# Constructor and destructor
class Person:
    def setFullName(self,firstName,lastName):
        self.firstname=firstName
        self.lastname=lastName   
    def getFullName(self):
        print(self.firstname," ",self.lastname)
    def __init__(self,id):
        self.id=id
        print("Our class is created with arg.")
    def __init__(self):
        print("Our class is created with no arg.")
    def __del__(self):
        print("Our object is destroyed.")
        
#### Add in shell to run from command line or remove comments for below lines            
#personName=Person()
#personName.setFullName("Jayesh","Hinge")
#personName.getFullName()
# Here *****self**** is the current class object to assign or use the value of variable
# always need to pass self as an argument in every method
# Concern: Not sure why after destructor called, i can call other methods as well
