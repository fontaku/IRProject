package lucene;

import java.io.IOException;
import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;

public class SearchEngine {
	
    private IndexSearcher searcher = null;
    private QueryParser parser = null;
    private Result[] lastResult;
    
    /** Creates a new instance of SearchEngine */
    public SearchEngine() throws IOException {
    	ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File((classLoader.getResource("index-directory-new")).getFile());
        searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(file)));
        parser = new QueryParser("content", new ThaiAnalyzer());
    }
    
    public TopDocs performSearch(String queryString, int n) throws IOException, ParseException {
        Query query = parser.parse(queryString);
        return searcher.search(query, n);
    }

    public Document getDocument(int docId) throws IOException {
        return searcher.doc(docId);
    }
    
    public String rebuildIndex(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
		String toPrint = "rebuildIndexes";
		Indexer indexer = new Indexer();
		indexer.rebuildIndexes();
		toPrint += "rebuildIndexes done";
		return toPrint;
	}
	
	public void searchPerformed(String query) throws IOException, ParseException{
        TopDocs topDocs = performSearch(query, 100);
        lastResult = new Result[topDocs.totalHits+1];
		lastResult[0] = new Result(0, 0, "You searched for " + query + "<br>Results found: " + topDocs.totalHits + "<br>", "", "", "", 0);
        ScoreDoc[] hits = topDocs.scoreDocs;
        for (int i = 0; i < hits.length; i++) {
            Document doc = getDocument(hits[i].doc);
            int size = query.length();
            String content = doc.get("content");
            String before = "";
            String exact = "";
            String after = "";
            for(int j=0; j+size<content.length(); j++){
            	if(content.substring(j, j+size).equals(query)){
            		int start = j-100;
            		if(start < 0){
            			start = 0;
            		}
            		int end = j+size+300;
            		if(end > content.length()){
            			end = content.length();
            		}
            		before = content.substring(start, j);
            		exact = content.substring(j, j+size);
            		after = content.substring(j+size, end);
            		break;
            	}
            }
            lastResult[i+1] = new Result(hits[i].doc, i+1, doc.get("id"), before, exact, after, hits[i].score);
        }
	}
	
	public Result[] getResult(int start, int size){
		if((lastResult.length - start) < size){
			size = lastResult.length - start;
		}
		Result[] temp = new Result[size+1];
		temp[0] = lastResult[0];
		for(int i=0; i<size; i++){
			temp[i+1] = lastResult[i+start];
		}
		return temp;
	}
	
	public int getTotalResult(){
		return lastResult.length;
	}
	
	public String getDocString(int id) throws IOException{
		Document doc = getDocument(id);
        String result = doc.get("content");
        result = "<pre>" + result;
        result = result.replaceAll("\n", "<br>");
        result = result.replaceAll("\t", "    ");
        result = result + "</pre>";
		String toPrint = "[" + doc.get("id") + "]<br><br>" + result + "<br><br>";
        return toPrint;
	}
}
