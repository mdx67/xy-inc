# XY-Inc

Exemplo de uma aplicação para cadastrar e mostrar pontos de interesse. Os pontos são cadastrados com coordenadas X, Y e é possível consultar todos os pontos e os pontos próximos.<br><br>
A interface é Rest, e utiliza Spring Boot e Java 8.
<br><br>

<b>Configuração:</b>

O banco de dados usado é o MySql. Dados já configurados:<br>
Username: root<br>
Password: root<br>
Url: jdbc:mysql://localhost/xyinc<br>

Servidor usado é o Tomcat 9.<br>

Para gerar o <code>.war</code> é necessário ter o Maven instalado e executar o comando: <code>mvn package</code> na pasta do projeto. 
O arquivo gerado fica na pasta <code>/target/XYInc.war</code><br>
Depois basta mover o arquivo para a pasta do tomcat <code>../webapps/XYInc.war</code>.

<br>

<b>Exemplos de utilização:</b>

- Adicionar ponto de interesse:<br>
<code>curl -H "Content-Type: application/json" -X POST -d '{"name": "Lanchonete", "x": "27", "y": "12"}' http://localhost:8080/XYInc/poi</code><br>

- Listar todos os pontos cadastrados:<br>
<code>curl -X GET "http://localhost:8080/XYInc/poi"</code><br>

- Listar pontos próximos a coordenada informada:<br>
<code>curl -X GET "http://localhost:8080/XYInc/poi/upcoming?x=20&y=10&d-max=10"</code><br>

<br><br><br><br>
