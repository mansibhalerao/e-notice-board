package notice_board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewStudentReq extends HttpServlet {

        Connection con;
        PreparedStatement ps;
        //now only once connection is setup when servlet is loaded to servlet container
        public void init()
        {
            
            try{String query = "select * from student where status = 'pending'";
             Class.forName("com.mysql.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/notice_board?useSSL=false","root","qwerty");
             ps = con.prepareStatement(query);
            }catch(Exception e){e.printStackTrace();}
         }
        public void destroy()
        {
            try{con.close();}catch(Exception e){e.printStackTrace();}  
        }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
            
        try{
            ResultSet rs = ps.executeQuery();
           
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>Pending Accounts</h3>");
            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<td>Id</td>");
            out.println("<td>Name</td>");
            out.println("<td>Branch</td>");
            out.println("<td>Sem</td>");
            out.println("</tr>");
            while(rs.next())
               {
                   String s1=rs.getString(1);
                   String s3=rs.getString(3);
                   String s4=rs.getString(4);
                   String s5=rs.getString(5);
                   out.println("<tr>");
                   out.println("<td>"+s1+"</td>");
                   out.println("<td>"+s3+"</td>");
                   out.println("<td>"+s4+"</td>");
                   out.println("<td>"+s5+"</td>");
                   out.println("<td><a href=enable_student_account?id="+s1+">Activate</a></td>"); 
                   out.println("</tr>");
               }
            out.println("</table>");
            out.println("<a href=admin_home.jsp>go back home</a>");
            out.println("</body>");
            out.println("</html>"); 
            }catch(Exception e){out.println(e);}
        }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
