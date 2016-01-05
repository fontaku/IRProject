package lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.ScoreDoc;

public class Main {
    
    public static void main(String[] args) {

      try {
    	if(false){
            System.out.println("rebuildIndexes");
            Indexer indexer = new Indexer();
            indexer.rebuildIndexes();
            System.out.println("rebuildIndexes done");
    	}

        System.out.println("performSearch");
        SearchEngine se = new SearchEngine();
        String query = "พระ";
        TopDocs topDocs = se.performSearch(query, 20);
        System.out.println("You searched for " + query);

        System.out.println("Results found: " + topDocs.totalHits);
        ScoreDoc[] hits = topDocs.scoreDocs;
        for (int i = 0; i < hits.length; i++) {
            Document doc = se.getDocument(hits[i].doc);
//            System.out.println(doc.get("id") + " " + doc.get("content") + " (" + hits[i].score + ")");
            System.out.println(doc.get("id") + " (" + hits[i].score + ")");

        }
        System.out.println("performSearch done");
      } catch (Exception e) {
        System.out.println("Exception caught.\n");
        e.printStackTrace();
      }
    }
    
}
