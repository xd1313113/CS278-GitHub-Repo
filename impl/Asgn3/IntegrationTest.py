from multiprocessing import Process
import time
import os
import socket
import shutil
import filecmp

#coding=utf8 

ServerPath = "server"
ClientPath = "client"
TimeOut = 5

#Start the server side
def Server():
    #createNewDir(ServerPath)  
    command ='java -jar JavaTestingExercise.jar '+ ServerPath
    os.system(command)

#start the client side
def Client():
    #createNewDir(ClientPath)
    serverip = socket.gethostbyname_ex(socket.gethostname())
    ip = serverip[2][1]
    print "connect to server:"+ ip 
    command ='java -jar JavaTestingExercise.jar '+ ServerPath + " " + ip
    os.system(command) 
  
# delete old directory if exists, and create a new one  
def createNewDir(name):     
    if not os.path.exists(name):
        os.mkdir(name)       

# delete old file if exists, and create a new one     
def createNewFile(name,content):     
    outFile = open(name,'w')
    outFile.write(content)
    outFile.close()

# update an existing file
def updateFile(name,content):
    outFile = open(name,'w')
    outFile.write(content)
    outFile.close()   

# wait for a certain seconds
def wait():
    time.sleep(TimeOut)  


def testClientAddUpdateRemoveFromClienttoServer():
    #test add
    add = ClientPath+"/"+"add.txt"
    createNewFile(add, "add")
    wait()
    if not os.path.exists(add):
        print "add from client Fails"
    elif not filecmp.cmp(add,  ServerPath+"/"+"add.txt"):
        print "add from client Fails"
    else:
        print "add from client Pass"
        
    #test update
    createNewFile(add, "add")
    updateFile(add,"update")
    wait()
    
    if not filecmp.cmp(add,  ServerPath+"/"+"add.txt"):
        print "update from client Fails"
    else:
        print "update from client Pass"
        
    #test remove
    remove = ClientPath+"/"+"remove.txt"
    createNewFile(remove, "remove")
    os.remove(remove)
    wait()
    if os.path.exists(remove):
        print "remove from client fails"
    else:
        print "remove from client pass"

def testClientAddUpdateRemoveFromServertoClient():
    #test add
    add = ServerPath+"/"+"add.txt"
    createNewFile(add, "add")
    wait()
    if not os.path.exists(add):
        print "add from client Fails"
    elif not filecmp.cmp(add,  ClientPath+"/"+"add.txt"):
        print "add from Server Fails"
    else:
        print "add from Server Pass"
        
    #test update
    createNewFile(add, "add")
    updateFile(add,"update")
    wait()
    
    if not filecmp.cmp(add,  ClientPath+"/"+"add.txt"):
        print "update from Server Fails"
    else:
        print "update from Server Pass"
        
    #test remove
    remove = ServerPath+"/"+"remove.txt"
    createNewFile(remove, "remove")
    os.remove(remove)
    wait()
    if os.path.exists(remove):
        print "remove from Server fails"
    else:
        print "remove from Server pass"
    
    
    
if __name__ == '__main__':
    print "begin integration testing"
    print os.path.abspath(__file__)
    print os.getcwd()
    print "Start server"
    createNewDir(ServerPath)  
    server = Process(target=Server)
    server.daemon = True
    server.start()
    server.join()
    
    time.sleep(10);
    print "Start client"
    createNewDir(ClientPath)  
    client = Process(target=Client)
    server.daemon = True
    client.start()
    client.join()
    time.sleep(10);
    
    print "testClientAddUpdateRemoveFromServertoClient"
    testClientAddUpdateRemoveFromServertoClient()
    
    print "testClientAddUpdateRemoveFromServertoClient"
    testClientAddUpdateRemoveFromServertoClient()
    
    #close the each process
    if client.is_alive():
        client.terminate()
    if server.is_alive():
        server.terminate()
        
    