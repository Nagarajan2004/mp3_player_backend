import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

@WebServlet("/song")
public class GetSongServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DB db = DB.getDb();
        Connection con = db.getCon();
        String query = "SELECT * FROM song_table";

        resp.setContentType("application/json");
        ArrayList<JSONObject> jsonArr = new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                JSONObject json = new JSONObject();
                json.put("song-title", rs.getString("song_title"));
                json.put("img", Base64.getEncoder().encodeToString(rs.getBytes("image")));
                json.put("audio", Base64.getEncoder().encodeToString(rs.getBytes("audio")));
                jsonArr.add(json);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        try(PrintWriter out = resp.getWriter()){
            out.println(jsonArr);
        }
    }
}
