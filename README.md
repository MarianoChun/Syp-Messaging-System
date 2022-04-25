<h1>Sistema de Mensajeria entre Espias</h1>

<h2>Descripción</h2>

<!--Modificar-->
<p> El sistema consiste en el envio de mensajes a todos los espías que se encuentran en
territorio enemigo. La comunicación se realiza de manera personal entre cada par de
espías:  se encuentran en un punto preacordado y se pasan el mensaje en un papel que se 
autodestruyeluego de unos segundos. No todos los pares de espías pueden encontrarse, y 
representamos esta situaci+ón por medio de un grafo G = (V, E) con un vértice por cada 
espía y una arista por cada par de espías que pueden encontrarse. Para cada arista ij ∈ E, 
tenemos además la probabilidad pij ∈ [0, 1] de que el enemigo intercepte a los espías 
durante el encuentro y se arruine el operativo.
Para enviarles el mismo mensaje a toda nuestra red de espías, buscamos un árbol generador de
G y los mensajes se transmiten a lo largo de las aristas de este árbol generador. Un cuello de
botella de un árbol generador es su arista de mayor peso. En nuestro caso, buscamos un árbol
generador con el cuello de botella más pequeño posible (es decir, buscamos minimizar el riesgo
del encuentro más peligroso). Este problema se denomina árbol generador con mínimo cuello
de botella. Todo árbol generador mínimo es también un árbol generador con mínimo cuello
de botella, de modo que podemos aplicar los Algoritmos de Prim o Kruskal para resolver este
problema.
</p>

<h2>Requerimientos</h2>

<ul>
    <li>
        Cargar los nombres de los espías y definir los pares de espías que se pueden encontrar
        junto con la probabilidad de intercepción de ese encuentro (es decir, cargar el grafo G
        y la función de peso p de las aristas).
    </li>
    <li>
        Resolver el problema de árbol generador con mínimo cuello de botella sobre los datos
        cargados, usando el Algoritmo de Prim o el Algoritmo de Kruskal.
    </li>
    <li>
        Mostrar los resultados de este algoritmo, informando qué pares de espías se deben
        encontrar y cuál es la probabilidad de intercepción de cada encuentro.
    </li>
</ul>

