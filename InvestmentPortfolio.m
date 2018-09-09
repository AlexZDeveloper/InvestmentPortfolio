%{ 
������ 124: ��������������
������: � ��� ���� �������� ������ �����: A1, A2, A3,..., An. 
���������� ����������������� ��� �� ��� ��������� ��������, �� ���, ����� ������� � ��������� ���� ��������� ���� �����������.
������� ������: Shares[] - ������ ����� �����, ��� Shares[i] - ���� i-� �����. 
������ ������� �� 1 �� 20, ���� ����� ����� �� 1 �� 10^3.
�����: FirstShares[], SecondShares[] - ������� � ������ ����� ��� 1�� � 2�� �������� ����-��, � ����� ��������� ������� �� ���������.
������: Shares = [1, 2, 3, 3]
Answer: 
FirstShares = [1, 3], FirstTotalValue = 4; 
SecondShares = [2, 3], SecondTotalValue = 5


������ ������ ������ � ������ ��������� ����������������.
����� ������ fn = {s1, s2 ... sn} - ������, ������� ���������� �������� ������ �����.
���������� ������� X1 = {x11, x12, x13 ... x1n}, X2 = {x21, x22, x23 ... x2n}, ��� x11, x12, x21 ... x1n, x2n ��������� �������� ������ 0, ��� 1, ������ x1i + x2i = 1.
E = {1, 1 ... 1} - ��������� ������. 
X1 + X2 = E
����� fn*X1 � fn*X2 - ����� ������������ ����� ������ � ������ �������� ������ �����.
���������� ������ ��������� ������:
|sum(fn*X1) - sum(fn*X2)| -> min
��������� X1 + X2 = E, �� ������ ������� ����� ���������� ���:
|sum(fn*X1) - sum(fn* (E - X1))| = |sum(fn*(X1 - (E - X1))| = |2 sum(fn*X1) - sum(fn)| -> min
���
    sum(fn*X1) -> min, 
    ��� �������:
        2 sum(fn*X1) >= sum(fn)
        X1 = {x1, x2, x3 ... xn}, ��� 0 <= xi <= 1, xi - ����� �����

����� �������, �� �������� ������������� ������ ��������� ����������������. 
���������� ��������� ������� ������� ������ ������ �����, �������� ����� ������. 
��, ��� ������� ������ ������, ������������� ����������� ���������� Matlab (������� glpk).
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