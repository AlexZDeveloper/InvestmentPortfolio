import java.util.ArrayList;
import java.util.Arrays;
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
 * Задача решена 2-мя способами
 * 1. Перебор всех возможных комбинаций ценных бумаг и нахождение той комбинации, при которой разница в стоимости этих портфелей была минимальной.
 * Для ускорения процесса, если нашли комбинацию, при которой разница в стоимости = 0, то дальнейший поиск прекращаем.
 * Комбинации находим путем перебора чисел от 0 до 2^n. Каждое такое число представляем в двоичном виде (010011..), кол-во цифр = кол-ву ценных бумаг.
 * Если на i-м месте стоит "1", то ценная бумага Аi попадает в первый портфель, если "0" - то во второй.
 * 
 * 
 * 2. Упорядочиваем ценные бумаги и берем по очереди все бумаги начиная с той, у которой стоимость наибольшая.
 * На каждом шаге ценную бумагу ложим в тот портфель, стоимость которого меньше, чем у воторого.
 * Данный способ позволяет решить задачу намного быстрее, но он имеет некоторую погрешность.
 * Данный алгоритм известен как "Жадный алгоритм задачи разбиения множества чисел", и даёт 7⁄6-аппроксимацию относительно оптимального решения оптимизационной версии.
 * Т.е. стоимость наибольшего портфеля найденного по данному алгоритму <= 7/6 стоимости наибольшего портфеля с оптимальным решением.
 * Данный подход можно применять если нужна высокая скорость и допускается небольшая погрешность в вычислениях. 
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
	
	
	public void calculateQuick(int[] shares) {
		firstShares.clear();
		secondShares.clear();
		Arrays.sort(shares);
    	int firstSum = 0;
    	int secondSum = 0;   
    	for (int i = shares.length - 1; i>=0; i--) {
    		if (firstSum < secondSum) {
    			firstShares.add(shares[i]);
    			firstSum += shares[i];
    		} else {
    			secondShares.add(shares[i]);
    			secondSum += shares[i];
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
    	
    	portfolio.calculateQuick(shares);
        portfolio.printShares();    	
	}
}
