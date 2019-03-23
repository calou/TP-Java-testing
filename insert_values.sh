curl -X PUT "http://localhost:8080/account" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"account_id\": \"a\",  \"balance\": 2000}"
curl -X PUT "http://localhost:8080/account" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"account_id\": \"b\",  \"balance\": 2000}"
curl -X PUT "http://localhost:8080/account" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"account_id\": \"c\",  \"balance\": 2000}"


curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"balance\": 200,  \"category\": \"SALARY\",  \"creditor\": \"b\",  \"debitor\": \"c\"}"
curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"balance\": 250,  \"category\": \"INTERESTS\",  \"creditor\": \"b\",  \"debitor\": \"c\"}"

curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"balance\": 500,  \"category\": \"SALARY\",  \"creditor\": \"a\",  \"debitor\": \"c\"}"
curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"balance\": 550,  \"category\": \"INTERESTS\",  \"creditor\": \"a\",  \"debitor\": \"c\"}"

curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"balance\": 550,  \"category\": \"LOAN\",  \"creditor\": \"a\",  \"debitor\": \"b\"}"
curl -X PUT "http://localhost:8080/transaction" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{  \"balance\": 50,  \"category\": \"MORTGAGE\",  \"creditor\": \"b\",  \"debitor\": \"a\"}"