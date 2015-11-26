package lucene;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;

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
    
    /** Creates a new instance of SearchEngine */
    public SearchEngine() throws IOException {
    	ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File(classLoader.getResource("index-directory").getFile());
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
	
	public Result[] searchPerformed(String query) throws IOException, ParseException{
        TopDocs topDocs = performSearch(query, 100);
        Result[] toPrint = new Result[topDocs.totalHits+1];
		toPrint[0] = new Result(0, 0, "You searched for " + query + "<br>Results found: " + topDocs.totalHits + "<br>", 0);
        ScoreDoc[] hits = topDocs.scoreDocs;
        for (int i = 0; i < hits.length; i++) {
            Document doc = getDocument(hits[i].doc);
            toPrint[i+1] = new Result(hits[i].doc, i+1, doc.get("id"), hits[i].score);

        }
        return toPrint;
	}
	
	public String getDocString(int id) throws IOException{
		Document doc = getDocument(id);
        String result = doc.get("content");
		String toPrint = "[" + doc.get("id") + "]<br><br>" + result + "<br><br>";
        return toPrint;
	}
}
