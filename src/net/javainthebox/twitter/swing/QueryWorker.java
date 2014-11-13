package net.javainthebox.twitter.swing;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

public class QueryWorker extends SwingWorker<QueryResult, Object> {

    private StatusTableModel model;
    private TwitterQuery twitterQuery;
    private String keyword;

    public QueryWorker(StatusTableModel model, TwitterQuery twitterQuery, String keyword) {
        this.model = model;
        this.twitterQuery = twitterQuery;
        this.keyword = keyword;
    }

    @Override
    protected QueryResult doInBackground() throws Exception {
        return twitterQuery.search(keyword);
    }

    @Override
    protected void done() {
        try {
            model.clear();

            QueryResult result = get();
            for (Status status : result.getTweets()) {
                model.addStatus(status);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(QueryWorker.class.getName()).log(Level.SEVERE, "Query Fail", ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(QueryWorker.class.getName()).log(Level.SEVERE, "Query Fail", ex);
        }
    }
}
