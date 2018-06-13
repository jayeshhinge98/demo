######Inheritance allows us to define calls in terms of another class.
class Parent:
    value1="Jayesh"
    value2="Hinge"
    def sum(self,a,b):
        return a+b
class Parent2():
    value3="FIGmd"
class Child (Parent,Parent2):
    pass
#    print("Value 1 is:",p.value1)
#    print("Value 2 is:",p1.value2) #will not work we need to add Parent2(Parent)
#    print("Value 3 is:",p1.value3)   

#p1=Parent2()
#p1.sum(20,30) 
#c=Child()
#c.value1
#c.value2
#c.value3
