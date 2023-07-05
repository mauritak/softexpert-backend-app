## Swagger
http://localhost:8090/api/rateio/swagger-ui.html#

![image](https://github.com/mauritak/softexpert-backend-app/assets/8314016/1b024c4d-bc65-4efc-82be-3d4e833267f1)


## CURL
```
curl -X POST "http://localhost:8090/api/rateio/calculos?habilitarLinkPagamento=false" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"pedidoParaRatearComAmigos\": { \"itensDoPedido\": [ { \"descricao\": \"Hamburger\", \"valor\": 40, \"quemPediu\": \"Mauricio\" },\t { \"descricao\": \"Sobremesa\", \"valor\": 2, \"quemPediu\": \"Mauricio\" } ], \"valorTaxaEntrega\": 8, \"valorDesconto\": 20 } , \"chave\": \"mauricio.takabayashi@gmail.com\"}"
```

# Json
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