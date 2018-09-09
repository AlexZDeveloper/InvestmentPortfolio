import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InvestmentPortfolioTest {
	private InvestmentPortfolio portfolio;
	
	@Before
	public void init() {
		portfolio = new InvestmentPortfolio();
	}
	
    @Test
    public void investmentPortfolioCase1() {
    	int[] shares = new int[] {1, 2, 3, 3};    	
    	portfolio.calculate(shares);
    	List<Integer> firstShares = portfolio.getFirstShares();
    	List<Integer> secondShares = portfolio.getSecondShares();
    	
        assertEquals(4, InvestmentPortfolio.getTotal(firstShares));
        assertEquals(5, InvestmentPortfolio.getTotal(secondShares));
        
        portfolio.calculateQuick(shares);
        firstShares = portfolio.getFirstShares();
    	secondShares = portfolio.getSecondShares();        
        assertEquals(4, InvestmentPortfolio.getTotal(firstShares));
        assertEquals(5, InvestmentPortfolio.getTotal(secondShares));
        
        portfolio.printShares();
    }
    
    @Test
    public void investmentPortfolioCase2() {
    	int[] shares = new int[] {10, 16, 82, 69, 69, 53, 13, 12, 96, 23};
    	
    	portfolio.calculate(shares);
    	List<Integer> firstShares = portfolio.getFirstShares();
    	List<Integer> secondShares = portfolio.getSecondShares();
    	
    	assertEquals(3, Math.abs(InvestmentPortfolio.getTotal(firstShares) - InvestmentPortfolio.getTotal(secondShares)));
    	portfolio.printShares();
   }
    
    @Test
    public void investmentPortfolioCase3() {
    	int[] shares = new int[] {4, 5, 6, 7, 8};    	
    	portfolio.calculate(shares);
    	List<Integer> firstShares = portfolio.getFirstShares();
    	List<Integer> secondShares = portfolio.getSecondShares();
    	portfolio.printShares();
    	
    	assertEquals(15, InvestmentPortfolio.getTotal(firstShares));
        assertEquals(15, InvestmentPortfolio.getTotal(secondShares));
    }    
    
    @Test
    public void investmentPortfolioCase4() {
    	int[] shares = new int[] {5, 1, 1, 9, 7, 3};
    	portfolio.calculate(shares);
    	List<Integer> firstShares = portfolio.getFirstShares();
    	List<Integer> secondShares = portfolio.getSecondShares();
    	portfolio.printShares();    	
    	assertEquals(13, InvestmentPortfolio.getTotal(firstShares));
        assertEquals(13, InvestmentPortfolio.getTotal(secondShares));
        
        portfolio.calculateQuick(shares);
        firstShares = portfolio.getFirstShares();
    	secondShares = portfolio.getSecondShares();    	
    	assertEquals(13, InvestmentPortfolio.getTotal(firstShares));
        assertEquals(13, InvestmentPortfolio.getTotal(secondShares));
    	portfolio.printShares();
    }
    
    
}
