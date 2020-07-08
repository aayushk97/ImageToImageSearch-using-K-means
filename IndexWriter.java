package imgsearch.indexing;

import ImgSearch.Constants;
import ImgSearch.clustering.Cluster;
import java.io.File;
import java.io.IOException;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

/**
 *
 * @author abc
 * @author abc
 */
public class IndexWriter extends Indexer {
    
    private org.apache.lucene.index.IndexWriter writer;

    public void open() throws CorruptIndexException, LockObtainFailedException, IOException {
        writer = new org.apache.lucene.index.IndexWriter(FSDirectory.open(new File(Constants.ROOT_IDX)),
                new SimpleAnalyzer(), true, org.apache.lucene.index.IndexWriter.MaxFieldLength.UNLIMITED);
    }

    public void addCluster(Cluster cluster) {
        try {
            String rep = parseMatrixs(cluster.getCentroid());
            //System.out.println(cluster.getCentroid().length);
            Document document = new Document();
            document.add(new Field(Constants.CENTROID_FIELD, rep, Field.Store.YES, Field.Index.NO));
            writer.addDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() throws CorruptIndexException, IOException {
        writer.optimize();
        writer.close();
    }
}
