package pkg.currency;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pkg.currencybean.Tarefa05Bean;

/**
 *
 * @author André
 */
public class CurrencyServlet extends HttpServlet {
    
    @EJB
    private Tarefa05Bean tarefa05Bean;

    BigDecimal valor;
    BigDecimal yen;
    BigDecimal euro;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Convert Currency</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<br>");
            String valueFromParameter = request.getParameter("ConvertToCurrency");
            Boolean dollarHasValue = 
                        (valueFromParameter != null) 
                        && 
                        (valueFromParameter.length() > 0);
            if(dollarHasValue){
                valor = new BigDecimal(valueFromParameter);
                
                yen = tarefa05Bean.convertDollarToYen(valor);
                
                euro = tarefa05Bean.convertYenToEuro(yen);
                
                out.println("<p>" + valor + " dollars equivalem a: " +
                        yen.toPlainString() + " yens.</p>");
                out.println("<p>" + yen.toPlainString() + " yens equivalem a: " +
                        euro.toPlainString() + " euros.</p>");
            }//if
            else {
                out.println("<p>Entre com um valor de dollar para converter:</p>");
                out.println("<form method=\"GET\">");
                out.println("<p>$ <input title=\"Valor\" type=\"text\" name=\"ConvertToCurrency\" size=\"25\"></p>");
                out.println("<br/>");
                out.println("<input type=\"submit\" value=\"Converter\">" +
                        "<input type=\"reset\" value=\"Reset\">");
                out.println("</form>");
            }//else
            out.println("</body>");
            out.println("</html>");
        }//try
    }//func

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}//class
