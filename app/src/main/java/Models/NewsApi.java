package Models;

import java.util.List;

public class NewsApi {
    String status;
    int totalResults;
    List<HeadLines> articles;


    public List<HeadLines> getArticles() {
        return articles;
    }

    public void setArticles(List<HeadLines> headLines) {
        this.articles = headLines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }


}
