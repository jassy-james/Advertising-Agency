INSERT INTO CLIENT (CLIENT_ID, NAME, PATRONYMIC, SURNAME, INFO) VALUES (1, '�������', '�������������', '����������', 'skype: daveyjones30');
INSERT INTO CLIENT (CLIENT_ID, NAME, PATRONYMIC, SURNAME, INFO) VALUES (2, '����', '�������������', '����������', 'skype: ilyaizya');
INSERT INTO CLIENT (CLIENT_ID, NAME, PATRONYMIC, SURNAME, INFO) VALUES (3, '�������', '���������', '������', 'skype: jeka0102');

INSERT INTO EMPLOYEE (EMPLOYEE_ID, NAME, PATRONYMIC, SURNAME, PHONE_NUMBER, POST) VALUES (1, '���������', '��������', '������', '88002000600', '���-��������');

INSERT INTO ORDERS (ORDER_ID, STATUS, CLIENT_ID, EMPLOYEE_ID) VALUES (1, '�� �����', 1, 1);
INSERT INTO ORDERS (ORDER_ID, STATUS, CLIENT_ID, EMPLOYEE_ID) VALUES (2, '�� �����', 2, 1);
INSERT INTO ORDERS (ORDER_ID, STATUS, CLIENT_ID, EMPLOYEE_ID) VALUES (3, '�� �����', 3, 1);
INSERT INTO PRODUCT (PRODUCT_ID, COST, DESCRIPTION, NAME, ORDER_ID) VALUES (1, 5000, '������������� ������', '�������������', 1);
INSERT INTO PRODUCT (PRODUCT_ID, COST, DESCRIPTION, NAME, ORDER_ID) VALUES (2, 10000, '���������� �������', '������', 1);
INSERT INTO PRODUCT (PRODUCT_ID, COST, DESCRIPTION, NAME, ORDER_ID) VALUES (3, 7000, '���������� �������', '������', 2);