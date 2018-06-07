#####while loop is like
a=1
s=0
lst=["one","two"]
print(lst)
print("Enter number to add in sum.")
print("or Enter 0 to quit")
while(a!=0):
    print("Current sum:",s)
    temp=input("Enter number:")
    a=int(temp)
    s+=a
    lst.append(temp)
print("Total sum is:",s)
print(lst)

#####for loop 
for num in lst:
    print(num)
