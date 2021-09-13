# Quote System

#### The goal of this project is to build a Quotes system that consumes Websocket Streams of our Partners and stores the data in an aggregated form for retrieval via a JSON-REST endpoint and a stream.

## Description

This application gets input stream from the partner-service jar. The connection is done via web socket protocol. 
<br>Server port is 8090<br>

##Tools
<li>Spring boot </li>
<li>Java 11</li>
<li>H2 database </li>
<li>Junit </li>
<li>Maven </li>

### Endpoints 
### Quote price history aggregator (Quotes) - **/quotes/price-history/{isin}**
The task is to build the Quotes system that consumes Websocket Streams of Partner service and stores the data in an aggregated form for retrieval via a JSON-REST endpoint and a stream.
## Instruments - **/api/v1/instruments?pageNumber={value}&pageSize={value}**
This provides the currently available instruments with their [ISIN]


