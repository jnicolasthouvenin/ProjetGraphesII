
function affArcs(A::Array{Arc,2})
    l = length(A)
    for ligne in 1:l
        for colonne in 1:l
            arc = false
            try
                arc = A[ligne,colonne]
            catch
            end
            if arc != false
                println("(",ligne,",",colonne,") = ",arc.r)
            end
        end
    end
end