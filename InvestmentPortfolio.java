import java.util.ArrayList;
import java.util.List;

/*
Задача 124: Диверсификация
Задача: у вас есть портфель ценных бумаг: A1, A2, A3,..., An. 
Необходимо диверсифицировать его на два отдельных портфеля, но так, чтобы разница в стоимости этих портфелей была минимальной.
Входные данные: Shares[] - массив ценых бумаг, где Shares[i] - цена i-й акции. 
Размер массива от 1 до 20, цена любой акции от 1 до 10^3.
Вывод: FirstShares[], SecondShares[] - массивы с ценами бумаг для 1го и 2го портфеля соот-но, а также стоимость каждого из портфелей.
Пример: Shares = [1, 2, 3, 3]
Answer: 
FirstShares = [1, 3], FirstTotalValue = 4; 
SecondShares = [2, 3], SecondTotalValue = 5
*/

/*
 * Способ решения
 * 1. Перебор всех возможных комбинаций ценных бумаг и нахождение той комбинации, при которой разница в стоимости этих портфелей была минимальной.
 * Для ускорения процесса, если нашли комбинацию, при которой разница в стоимости = 0, то дальнейший поиск прекращаем.
 * Комбинации находим путем перебора чисел от 0 до 2^n. Каждое такое число представляем в двоичном виде (010011..), кол-во цифр = кол-ву ценных бумаг.
 * Если на i-м месте стоит "1", то ценная бумага Аi попадает в первый портфель, если "0" - то во второй.
 * */
public class InvestmentPortfolio 
{
	List<Integer> firstShares = new ArrayList<>();
	List<Integer> secondShares = new ArrayList<>();
	
    public InvestmentPortfolio() {		
	}

	public void calculate(int[] shares) {
		firstShares.clear();
		secondShares.clear();
		
		List<Integer> tmpFirstShares = new ArrayList<>();
		List<Integer> tmpSecondShares = new ArrayList<>();
		
		int cnt = (int) Math.pow(2, shares.length);
		int delta = Integer.MAX_VALUE; 
		
		for (int k = 0; k < cnt; k++) {
			tmpFirstShares.clear();
			tmpSecondShares.clear();			
			
			for (int i = 0; i < shares.length; i++) {				
				if ((k & (1<<i)) > 0) {
					tmpFirstShares.add(shares[i]);					
				} else {
					tmpSecondShares.add(shares[i]);
				}
			}
			
			if ((int) Math.abs(getTotal(tmpFirstShares) - getTotal(tmpSecondShares)) < delta) {
				delta = (int) Math.abs(getTotal(tmpFirstShares) - getTotal(tmpSecondShares));
				firstShares.clear();
				secondShares.clear();
				firstShares.addAll(tmpFirstShares);
				secondShares.addAll(tmpSecondShares);
				if (delta == 0) {
					return;
				}
			}
			
		}
	}	

	public List<Integer> getFirstShares() {
		return firstShares;		
	}

	public List<Integer> getSecondShares() {
		return secondShares;
	}

	public static int getTotal(List<Integer> shares) {
		return shares.stream().mapToInt(x -> x).sum();
	}
	
	
	public void printShares() {
    	System.out.print("First shares: ");
    	firstShares.forEach(x -> System.out.print(x + "; "));
    	System.out.print("Total: " + InvestmentPortfolio.getTotal(firstShares));
    	System.out.println();
    	
    	System.out.print("Second shares: ");
    	secondShares.forEach(x -> System.out.print(x + "; "));
    	System.out.print("Total: " + InvestmentPortfolio.getTotal(secondShares));
    	System.out.println();
    }
	
	
	public static void main(String[] args) {
    	int[] shares = new int[] {1, 2, 3, 3};
    	InvestmentPortfolio portfolio = new InvestmentPortfolio();
    	portfolio.calculate(shares);
    	portfolio.printShares(); 
	}
}
