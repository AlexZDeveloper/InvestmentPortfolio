%{ 
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


Данную задачу сведем к задаче линейного программирования.
Пусть вектор fn = {s1, s2 ... sn} - вектор, который отображает портфель ценных бумаг.
Рассмотрим векторы X1 = {x11, x12, x13 ... x1n}, X2 = {x21, x22, x23 ... x2n}, где x11, x12, x21 ... x1n, x2n принимают значения только 0, или 1, причем x1i + x2i = 1.
E = {1, 1 ... 1} - еденичный вектор. 
X1 + X2 = E
Тогда fn*X1 и fn*X2 - будут представлять собой первый и второй портфели ценных бумаг.
Необходимо решить следующую задачу:
|sum(fn*X1) - sum(fn*X2)| -> min
Поскольку X1 + X2 = E, то данное условие можно переписать так:
|sum(fn*X1) - sum(fn* (E - X1))| = |sum(fn*(X1 - (E - X1))| = |2 sum(fn*X1) - sum(fn)| -> min
Или
    sum(fn*X1) -> min, 
    при условии:
        2 sum(fn*X1) >= sum(fn)
        X1 = {x1, x2, x3 ... xn}, где 0 <= xi <= 1, xi - целые числа

Таким образом, мы получили целочисленную задачу линейного программирования. 
Существоет множество методов решения такого класса задач, например метод Гомори. 
Мы, для решения данной задачи, воспользуемся встроинными средствами Matlab (функция glpk).
%}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

shares = [1, 2, 3, 3];

fn = shares';
A = 2 * shares;
b = sum(shares);

share_size = length(shares);
lb = zeros(share_size, 1);
ub = ones(share_size, 1);

ctype = "L";    % An inequality with a lower bound (A(i,:)*x >= b(i))
vartype = char(ones(share_size,1) * 'I');   % All variables are Integer
sense = 1;      % Minimization problem

[xmin] = glpk (fn, A, b, lb, ub, ctype, vartype, sense);
x1 = shares.*xmin';
x2 = shares - x1;

firstShares = x1(x1 ~=0)  % removing zero elements
firstSharesTotal = sum(firstShares)
secondShares = x2(x2 ~=0) % removing zero elements
secondSharesTotal = sum(secondShares)