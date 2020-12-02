
mutable struct Sommet
    e::Int
    h::Int
end

mutable struct Arc
    r::Int
end

const LINE = "--------------------------------------------------------"

include("tools.jl")

function main()
    # Structure du graphe
    M = [
        0 1 1 1 0 0 0 0 ;
        0 0 0 0 1 1 0 0 ;
        0 0 0 0 1 0 1 0 ;
        0 0 0 0 0 1 1 0 ;
        0 0 0 0 0 0 0 1 ;
        0 0 0 0 0 0 0 1 ;
        0 0 0 0 0 0 0 1 ;
        0 0 0 0 0 0 0 0
    ]
    D = [[2,3,4],[5,6,7],[5,6,7],[5,6,7],[8],[8],[8],[]]
    # Sommets
    S = [ # pour économiser de la place
        Sommet(typemax(Int),8),Sommet(0,0),Sommet(0,0),Sommet(0,0),Sommet(0,0),Sommet(0,0),Sommet(0,0),Sommet(0,0)
        ]
    # Voisins
    V = Vector{Vector{Int}}(undef,8)
    V[1] = [2,3,4]
    V[2] = [1,5,6]
    V[3] = [1,5,7]
    V[4] = [1,6,7]
    V[5] = [2,3,8]
    V[6] = [2,4,8]
    V[7] = [3,4,8]
    V[8] = [5,6,7]
    # Arcs
    A = Matrix{Arc}(undef,8,8)

    A[1,2] = Arc(6)
    A[2,1] = Arc(0)

    A[1,3] = Arc(1)
    A[3,1] = Arc(0)

    A[1,4] = Arc(1)
    A[4,1] = Arc(0)

    A[2,5] = Arc(typemax(Int))
    A[5,2] = Arc(0)

    A[2,6] = Arc(typemax(Int))
    A[6,2] = Arc(0)

    A[3,5] = Arc(typemax(Int))
    A[5,3] = Arc(0)

    A[3,7] = Arc(typemax(Int))
    A[7,3] = Arc(0)

    A[4,6] = Arc(typemax(Int))
    A[6,4] = Arc(0)

    A[4,7] = Arc(typemax(Int))
    A[7,4] = Arc(0)

    A[5,8] = Arc(0)
    A[8,5] = Arc(0)

    A[6,8] = Arc(5)
    A[8,6] = Arc(0)

    A[7,8] = Arc(7)
    A[8,7] = Arc(0)

    #=M = [
        0 1 1 0 0 ;
        0 0 0 1 0 ;
        0 1 0 1 0 ;
        0 0 0 0 1 ;
        0 0 0 0 0
    ]

    D = [[2,3],[4],[2,4],[5],[]]

    # Sommets
    S = [ # pour économiser de la place
        Sommet(typemax(Int),5),Sommet(0,0),Sommet(0,0),Sommet(0,0),Sommet(0,0)
        ]
    
    # Voisins
    V = Vector{Vector{Int}}(undef,5)
    V[1] = [2,3]
    V[2] = [1,3,4]
    V[3] = [1,2,4]
    V[4] = [2,3,5]
    V[5] = [4]

    # Arcs
    A = Matrix{Arc}(undef,5,5)

    A[1,2] = Arc(1)
    A[2,1] = Arc(0)

    A[1,3] = Arc(3)
    A[3,1] = Arc(0)

    A[3,2] = Arc(1)
    A[2,3] = Arc(0)

    A[2,4] = Arc(1)
    A[4,2] = Arc(0)

    A[3,4] = Arc(2)
    A[4,3] = Arc(0)

    A[4,5] = Arc(2)
    A[5,4] = Arc(0)=#

    #A = preflots(S,A,V)
    preflots_avant(S,A,V)
    affArcs(A)

    for sommet in S
        println(sommet)
    end

    return true
end

function preflots_avant(S,A,V)
    nbSommets = length(S)
    listeSommets = Vector{Int}(2:(nbSommets-1))
    # initialisation
    for voisin in V[1] # voisins de s
        pousser(S,1,voisin,A)
    end

    indiceListe = 1
    debug = 1
    while indiceListe <= nbSommets-2 && debug <= 100
        println("[listeSommets] = ",listeSommets)
        println("indiceListe = ",indiceListe)
        debug += 1
        sommet = listeSommets[indiceListe]
        #println("sommet = ",sommet)
        hauteurPrecedente = S[sommet].h
        hauteurModifiee = decharger(S,sommet,V,A)
        if hauteurModifiee # on place le sommet en tete de liste
            listeSommets[indiceListe] = listeSommets[1]
            listeSommets[1] = sommet
            indiceListe = 1
        else
            indiceListe += 1
        end
    end
end

function decharger(S::Array{Sommet,1},sommet::Int,V::Vector{Vector{Int}},A::Array{Arc,2})
    h = S[sommet].h
    debug = 1
    pousseeReussie = false
    println(LINE)
    println("[decharger] ",sommet)
    while S[sommet].e > 0 && debug <= 100
        debug += 1
        for voisin in V[sommet]
            pousseeReussie = pousser(S,sommet,voisin,A)
        end
        elever(S,sommet,V,A)
    end
    println("     ",h < S[sommet].h)
    return h < S[sommet].h || pousseeReussie
end

function pousser(S::Array{Sommet,1},u::Int,v::Int,A::Array{Arc,2};sym=false)
    arc = A[u,v]

    #=println("-----------------------")
    println("on essaye de pousser (",u,",",v,")")
    println("u = ",S[u])
    println("v = ",S[v])
    println("arc = ",arc)=#

    if S[u].e > 0 && arc.r > 0 && S[u].h > S[v].h
        println(LINE)
        println("   [pousser] (",u,",",v,")")
        flot = min(S[u].e, arc.r)
        A[u,v].r -= flot
        A[v,u].r += flot
        S[u].e -= flot
        S[v].e += flot
        println("       [fin poussee]")
        println("       u = ",S[u])
        println("       v = ",S[v])
        #=println("//////////////////")
        affArcs(A)
        println("//////////////////")=#
        println(affDebordants(S))
        return true
    else
        #println("erreur precondition [pousser] (",u,",",v,") : les conditions ne sont pas respectées !")
        return false
    end
end

function elever(S::Array{Sommet,1},indiceSommet::Int,V::Vector{Vector{Int}},A::Array{Arc,2})
    #println("-----------------------")
    # verification que le sommet déborde
    if S[indiceSommet].e <= 0
        #println("erreur precondition [elever] : le sommet ne déborde pas !")
        return false
    end

    # verification que les voisins sont tous trop eleves (on en profite pour calculer la hauteur minimum)
    minH = Inf
    for indiceVoisin in V[indiceSommet] # parcours des sommets voisins à indiceSommet
        hauteurVoisin = S[indiceVoisin].h
        if hauteurVoisin < S[indiceSommet].h && A[indiceSommet,indiceVoisin].r > 0
            #println("erreur precondition [elever] : un des voisins est plus bas que U !")
            return false
        end
        if hauteurVoisin < minH # on a trouvé une nouvelle hauteur minimum
            minH = hauteurVoisin # mise à jour de hauteur minimum
        end
    end
    
    println(LINE)
    println("   [elever] ",indiceSommet)

    # modification de la hauteur
    if S[indiceSommet].h < 1 + minH
        S[indiceSommet].h = 1 + minH
    else
        S[indiceSommet].h += 1
    end

    #println("indiceSommet = ",S[indiceSommet])

    return true
end

function affDebordants(S::Array{Sommet,1})
    println(LINE)
    println("[Sommets débordants]")
    for i in 1:length(S)
        sommet = S[i]
        if sommet.e > 0
            println("[",i,"] : e = ",sommet.e)
        end
    end
end

#= ARCHIVES
S = [
        Sommet(0,0),
        Sommet(0,0),
        Sommet(0,0),
        Sommet(0,0),
        Sommet(0,0),
        Sommet(0,0),
        Sommet(0,0),
        Sommet(0,0),
    ]

    S = Vector{Sommet}(undef,8)



function preflots(S::Array{Sommet,1},A::Array{Arc,2},V::Vector{Vector{Int}})
    println("[preflots]")
    nbSommets = 8

    # on test toutes les poussées possibles et les élévation possibles, si on en réussit une on recommence.
    pousseeEffectuee = true
    elevationEffectuee = true
    while pousseeEffectuee || elevationEffectuee
        println("while principal")
        # on test toutes les poussées possibles
        pousseeEffectuee = true
        while pousseeEffectuee # tant qu'on peut effectuer une poussee on l'effectue
            println("on test toutes les poussées possibles")
            indiceU = 1
            indiceV = 1
            pousseeEffectuee = false
            while !pousseeEffectuee && indiceU <= nbSommets
                indiceV = 1
                while !pousseeEffectuee && indiceV <= nbSommets
                    if indiceU != indiceV
                        println("poussage : (",indiceU,",",indiceV,")")
                        pousseeEffectuee = pousser(S,indiceU,indiceV,A)
                        if !pousseeEffectuee
                            pousseeEffectuee = pousser(S,indiceU,indiceV,A,sym=true)
                        end
                    end
                    indiceV += 1
                end
                indiceU += 1
            end

            indiceU = 1
            indiceV = 1
            pousseeEffectuee = false
            while !pousseeEffectuee && indiceU <= nbSommets
                nbVoisins = length(V[indiceU])
                indiceV = 1
                while !pousseeEffectuee && indiceV <= nbVoisins
                    if indiceU != indiceV
                        println("poussage : (",indiceU,",",indiceV,")")
                        pousseeEffectuee = pousser(S,indiceU,indiceV,A)
                    end
                    indiceV += 1
                end
                indiceU += 1
            end
        end

        # on test toutes les élévations possibles
        elevationEffectuee = true
        while elevationEffectuee
            println("on test toutes les élévations possibles")
            indiceSommet = 1
            elevationEffectuee = false
            while !elevationEffectuee && indiceSommet <= nbSommets
                elevationEffectuee = elever(S,indiceSommet,V)
                indiceSommet += 1
            end
        end
    end

    return A
end
=#