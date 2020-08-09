package pkg.currencybean;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author André
 */
@Stateless
@LocalBean
public class Tarefa05Bean {

    //1 Dollar americano = 105,90 Iene japonês (09/08/2020)
    public BigDecimal convertDollarToYen(BigDecimal dollar) {
        BigDecimal yen = dollar.multiply(BigDecimal.valueOf(105.90)); 
        return yen;
    }//func
    
    //1 Yen japonês = 0,0080 Euros (09/08/2020)
    public BigDecimal convertYenToEuro(BigDecimal yen) {
        BigDecimal euro = yen.multiply(BigDecimal.valueOf(0.0080));
        return euro; 
    }//func
}//class
