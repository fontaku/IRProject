package lucene;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field; 
import org.apache.lucene.document.StringField; 
import org.apache.lucene.document.TextField; 
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	
	public Indexer() {
    }
 
    private IndexWriter indexWriter = null;
    
    public IndexWriter getIndexWriter(boolean create) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (indexWriter == null) {
            Directory indexDir = FSDirectory.open(new File("index-directory-for-test"));
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, new ThaiAnalyzer());
            indexWriter = new IndexWriter(indexDir, config);
        }
        return indexWriter;
   }    
   
    public void closeIndexWriter() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
        }
   }
    
    public void index(String id, String content) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//        String toPrint = "Indexing: " + id;
        IndexWriter writer = getIndexWriter(false);
        Document doc = new Document();
        doc.add(new StringField("id", id, Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));
        writer.addDocument(doc);
//        return toPrint;
    }
    
    public void rebuildIndexes() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
          //
          // Erase existing index
          //
          getIndexWriter(true);
          //
          // Index all Accommodation entries
          //          
          Files.walk(Paths.get("data-for-test/")).forEach(filePath -> {
      	    if (Files.isRegularFile(filePath)) {
      	    	Path fileName = filePath.getFileName();
      	    	String data = "";
      	    	try {
  					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath.toString())));
  					for(String line = reader.readLine(); line != null; line = reader.readLine()){
  						data += line + "\n";
  					}
  					index(fileName.getFileName().toString(), data);
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
      	    }});
          
          //
          // Don't forget to close the index writer when done
          //
          closeIndexWriter();
     }    
}
