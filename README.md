 ---- English ---- 
 ===================
<h1>Spy messaging system</h1>
<h2>Description of the problem</h2>

<p>
    The system consist in the sending of messages to all the spies that are in enemy territory. The communication takes place between two pair of spies, they meet in a preacorded place and they receive the message which is in a paper that will autodestroy in seconds. Not every pair of spies can meet, so we will represent the situation with a graph G = (V,E) with a vertex for every spy and an edge for every pair of spies that can meet. For every edge ij ∈ E, we have a probability pij ∈ [0, 1] that the enemy can catch our spies during the meeting and ruin the mission. To send the same message to the whole spy network, we will build a spanning tree of G and the messagges will be sent through the edges of this tree. We will seek to build a spanning  tree with minimum bottleneck, a bottleneck is the edge with the greatest weigth (probability) of the graph. (We are looking to minimize the risk of the most risky meeting). Given this, we will obtain this graph with the algorithms of Prim and Kruskal, that solve both the problem of minimum spanning tree and spanning tree with minimum bottleneck.
</p>

<h2>Requirements</h2>

<ul>
    <li>
        Load the names of the spies and define the pairs of spies that can meet along with the probability of interception of that meeting. (In other words, load the graph G and the weight function of the edges).
    </li>
    <li>
        Resolve the problem of spanning tree with minimum bottleneck on loaded data, using the algorithms of Prim or Kruskal.
    </li>
    <li>
        Show the results of the algorithms, informing which pairs of spies must meet and which is the probability of interception of every meeting.
    </li>
</ul>

---- Español ---- 
 ===================
<h1>Sistema de Mensajeria entre Espias</h1>
<h2>Descripción del problema</h2>

<p>El sistema consiste en el envio de mensajes a todos los espías que se encuentran en
territorio enemigo. La comunicación se realiza de manera personal entre cada par de
espías:  se encuentran en un punto preacordado y se pasan el mensaje en un papel que se 
autodestruyeluego de unos segundos. No todos los pares de espías pueden encontrarse, y 
representamos esta situación por medio de un grafo G = (V, E) con un vértice por cada 
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

