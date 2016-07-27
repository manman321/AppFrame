package com.wlht.oa.net;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
/**
 * Created by hr on 16/7/21.
 */
public class Reptile {

    public static final String ARTICLE_BASE_URL = "";
    public static final String COUNT_BASE_URL = "";

    public static String inToStringByByte(InputStream in) throws Exception {
        ByteArrayOutputStream outStr = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        StringBuilder content = new StringBuilder();
        while ((len = in.read(buffer)) != -1) {
            content.append(new String(buffer, 0, len, "UTF-8"));
        }
        outStr.close();
        return content.toString();
    }

    public static String doGet(String urlStr) throws Exception
    {
        URL url;
        String html = "";
        try {

            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (connection.getResponseCode() == 200)
            {
                InputStream in = connection.getInputStream();
                html = inToStringByByte(in);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return html;
    }


    public static ArticleItem getNewsItem(int currentPage) throws Exception {
        // 根据后缀的数字，拼接新闻 url
        String urlStr = ARTICLE_BASE_URL + currentPage + ".html";

        String htmlStr = doGet(urlStr);

        Document doc = Jsoup.parse(htmlStr);

        Element articleEle = doc.getElementById("article");
        // 标题
        Element titleEle = articleEle.getElementById("article_title");
        String titleStr = titleEle.text();

        // article_detail包括了 2016-01-15 来源: 浏览次数:177
        Element detailEle = articleEle.getElementById("article_detail");
        Elements details = detailEle.getElementsByTag("span");

        // 发布时间
        String dateStr = details.get(0).text();

        // 新闻来源
        String sourceStr = details.get(1).text();

        // 访问这个新闻页面，浏览次数会+1，次数是 JS 渲染的
        String jsStr = doGet(COUNT_BASE_URL + currentPage);
        int readTimes = Integer.parseInt(jsStr.replaceAll("\\D+", ""));
        // 或者使用下面这个正则方法
        // String readTimesStr = jsStr.replaceAll("[^0-9]", "");

        Element contentEle = articleEle.getElementById("article_content");
        // 新闻主体内容
        String contentStr = contentEle.toString();
        // 如果用 text()方法，新闻主体内容的 html 标签会丢失
        // 为了在 Android 上用 WebView 显示 html，用toString()
        // String contentStr = contentEle.text();

        Elements images = contentEle.getElementsByTag("img");
        String[] imageUrls = new String[images.size()];
        for (int i = 0; i < imageUrls.length; i++) {
            imageUrls[i] = images.get(i).attr("src");
        }

        return new ArticleItem(currentPage, imageUrls, titleStr, dateStr, sourceStr, readTimes, contentStr);

    }

    public static void main(String[] args) throws Exception {
        System.out.println(getNewsItem(7928));
    }


    public static class ArticleItem {

        private int index;
        private String[] imageUrls;
        private String title;
        private String publishDate;
        private String source;
        private int readTimes;
        private String body;

        public ArticleItem(int index, String[] imageUrls, String title, String publishDate, String source, int readTimes,
                           String body) {
            this.index = index;
            this.imageUrls = imageUrls;
            this.title = title;
            this.publishDate = publishDate;
            this.source = source;
            this.readTimes = readTimes;
            this.body = body;
        }

        @Override
        public String toString() {
            return "ArticleItem [index=" + index + ",\n imageUrls=" + Arrays.toString(imageUrls) + ",\n title=" + title
                    + ",\n publishDate=" + publishDate + ",\n source=" + source + ",\n readTimes=" + readTimes + ",\n body=" + body
                    + "]";
        }


    }

}
