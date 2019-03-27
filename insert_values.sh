curl -X PUT "http://localhost:8080/account" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"account_id\": \"a\",  \"balance\": 2000}"
curl -X PUT "http://localhost:8080/account" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"account_id\": \"b\",  \"balance\": 2000}"
curl -X PUT "http://localhost:8080/account" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"account_id\": \"c\",  \"balance\": 2000}"


curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"amount\": 20,  \"category\": \"SALARY\",  \"creditor\": \"b\",  \"debitor\": \"c\"}"
curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"amount\": 25,  \"category\": \"INTERESTS\",  \"creditor\": \"b\",  \"debitor\": \"c\"}"

curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"amount\": 50,  \"category\": \"SALARY\",  \"creditor\": \"a\",  \"debitor\": \"c\"}"
curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"amount\": 55,  \"category\": \"INTERESTS\",  \"creditor\": \"a\",  \"debitor\": \"c\"}"

curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"amount\": 55,  \"category\": \"LOAN\",  \"creditor\": \"a\",  \"debitor\": \"b\"}"
curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"amount\": 50,  \"category\": \"MORTGAGE\",  \"creditor\": \"b\",  \"debitor\": \"a\"}"
