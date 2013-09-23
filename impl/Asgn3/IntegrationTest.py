import filecmp
import os
import socket
import subprocess
import time


#coding=utf8 
ServerPath = "server"
ClientPath = "client"
TimeOut = 30
output = "result.txt"

def logToFile(content):       
    outFile = open(output,'a')
    outFile.write(content)
    outFile.flush()
    outFile.close()
  
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


def testAddUpdateRemoveFromClienttoServer():
    #test add
    add = ClientPath+"/"+"add.txt"
    createNewFile(add, "add")
    wait()
    if not os.path.exists(ServerPath+"/"+"add.txt"):
        logToFile( "add from client Fails")
    else:
        if not filecmp.cmp(add,  ServerPath+"/"+"add.txt"):
            logToFile( "add from client Fails")
        else:
            logToFile( "add from client Pass" )
        
    #test update
    createNewFile(add, "add")
    updateFile(add,"update")
    wait()
    
    if not os.path.exists(ServerPath+"/"+"add.txt"):
        logToFile( "update from client Fails")
    else:
        if not filecmp.cmp(add,  ServerPath+"/"+"add.txt"):
            logToFile( "update from client Fails")
        else:
            logToFile( "update from client Pass")
        
    #test remove
    remove = ClientPath+"/"+"remove.txt"
    createNewFile(remove, "remove")
    os.remove(remove)
    wait()
    if os.path.exists(remove):
        logToFile( "remove from client fails")
    else:
        logToFile(  "remove from client pass")

def testAddUpdateRemoveFromServertoClient():
    #test add
    add = ServerPath+"/"+"add.txt"
    createNewFile(add, "add")
    wait()
    if not os.path.exists(ClientPath+"/"+"add.txt"):
        logToFile( "add from client Fails")
    else:
        if not filecmp.cmp(add,  ClientPath+"/"+"add.txt"):
            logToFile( "add from Server Fails")
        else:
            logToFile( "add from Server Pass" )
        
    #test update
    createNewFile(add, "add")
    updateFile(add,"update")
    wait()
    if not os.path.exists(ClientPath+"/"+"add.txt"):
        logToFile( " from client Fails")
    else:    
        if not filecmp.cmp(add,  ClientPath+"/"+"add.txt"):
            logToFile( "update from Server Fails")
        else:
            logToFile( "update from Server Pass" )
        
    #test remove
    remove = ServerPath+"/"+"remove.txt"
    createNewFile(remove, "remove")
    os.remove(remove)
    wait()
    if os.path.exists(remove):
        logToFile(  "remove from Server fails")
    else:
        logToFile(  "remove from Server pass" )
    
def test():
    print "testClientAddUpdateRemoveFromClienttoServer"
    testAddUpdateRemoveFromClienttoServer()
    
    print "testClientAddUpdateRemoveFromServertoClient"
    testAddUpdateRemoveFromServertoClient()    
    
if __name__ == '__main__':
    print "begin integration testing"
    print os.path.abspath(__file__)
    print os.getcwd()
    
    print "Start server"
    createNewDir(ServerPath)  
    server = subprocess.Popen('java -jar JavaTestingExercise.jar '+ ServerPath, shell=True)
    time.sleep(30);
    print "Start client"
    createNewDir(ClientPath)  
    serverip = socket.gethostbyname_ex(socket.gethostname())
    ip = serverip[2][1]
    print "connect to server:"+ ip 
    client =subprocess.Popen('java -jar JavaTestingExercise.jar '+ ServerPath + " " + ip, shell=True)
    time.sleep(30);
    #start testing
    test()
    server.terminate()
    client.terminate()
    
    

    
    
    
    

    

        
    