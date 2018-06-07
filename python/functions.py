def add(a,b):
    return a+b
def studentScore(name,*score):
    if name=="" and score==0:
        print("Name and score is empty. please enter names again")
        name=input("Name? ")
        score=input("Marks? ")
        studentScore(name,score)
    else:
        print("Student ",name,' scored ',score,' marks.')
