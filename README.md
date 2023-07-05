### | Swagger
http://localhost:8090/api/rateio/swagger-ui.html#

![image](https://github.com/mauritak/softexpert-backend-app/assets/8314016/1b024c4d-bc65-4efc-82be-3d4e833267f1)


#### | CURL
```
curl -X POST "http://localhost:8090/api/rateio/calculos?habilitarLinkPagamento=false" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"pedidoParaRatearComAmigos\": { \"itensDoPedido\": [ { \"descricao\": \"Hamburger\", \"valor\": 40, \"quemPediu\": \"Mauricio\" },\t { \"descricao\": \"Sobremesa\", \"valor\": 2, \"quemPediu\": \"Mauricio\" } ], \"valorTaxaEntrega\": 8, \"valorDesconto\": 20 } , \"chave\": \"mauricio.takabayashi@gmail.com\"}"
```


#### | O back-end utiliza a API do Itau para gerar o link de pagamento
https://devportal.itau.com.br/baas/#/catalog?sortingType=0&currentPage=1&selOpQtdRegister=0

![image](https://github.com/mauritak/softexpert-frontend-app/assets/8314016/71c51bd2-158f-4aa5-b41b-9546bc89b630)



#### | Json Entrada para operacao /calculos
```
{
  "pedidoParaRatearComAmigos": {
    "itensDoPedido": [
      {
        "descricao": "Hamburger",
        "valor": 40,
        "quemPediu": "Mauricio"
      },
	  {
        "descricao": "Sobremesa",
        "valor": 2,
        "quemPediu": "Mauricio"
      },
	  {
        "descricao": "Sanduiche(Amigo)",
        "valor": 8,
        "quemPediu": "Amigo"
      }
    ],
    "valorTaxaEntrega": 8,
    "valorDesconto": 20
  }
  ,
  "chave": "mauricio.takabayashi@gmail.com"
}
```
####  Exemplo do resultado da operacao bem sucedida
```
{
  "rateio": {
    "valorTotalAPagar": 38,
    "valorAPagarPorPessoa": {
      "Amigo": 6.08,
      "Mauricio": 31.92
    },
    "linksPagamento": [
      {
        "identificaçãoDevedor": "Amigo",
        "valorAPagar": 6.08,
        "pixLink": "pix.example.com/qr/v2/9d36b84fc70b478fb95c12729b90ca25",
        "qrcode": null
      },
      {
        "identificaçãoDevedor": "Mauricio",
        "valorAPagar": 31.92,
        "pixLink": "pix.example.com/qr/v2/9d36b84fc70b478fb95c12729b90ca25",
        "qrcode": null
      }
    ]
  }
}
```
