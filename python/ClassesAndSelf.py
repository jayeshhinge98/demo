# Class and Self
class Person:
    def setFullName(self,firstName,lastName):
        self.firstname=firstName
        self.lastname=lastName   
    def getFullName(self):
        print(self.firstname," ",self.lastname)        
    def add(self,a,b):
        return a+b
    def studentScore(self,name,*score):
        if name=="" and score==0:
            print("Name and score is empty. please enter names again")
            name=input("Name? ")
            score=input("Marks? ")
            studentScore(name,score)
        else:
            print("Student ",name,' scored ',score,' marks.')
#### Add in shell to run from command line or remove comments for below lines            
#personName=Person()
#personName.setFullName("Jayesh","Hinge")
#personName.getFullName()
# Here *****self**** is the current class object to assign or use the value of variable
# always need to pass self as an argument in every method
