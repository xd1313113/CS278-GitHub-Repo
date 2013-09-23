import filecmp
from multiprocessing import Pool
import os
import shutil
import socket
import time
import subprocess


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


def testAddUpdateRemoveFromClienttoServer():
    #test add
    add = ClientPath+"/"+"add.txt"
    createNewFile(add, "add")
    wait()
    if not os.path.exists(add):
        logToFile( "add from client Fails")
    elif not filecmp.cmp(add,  ServerPath+"/"+"add.txt"):
        logToFile( "add from client Fails")
    else:
        logToFile( "add from client Pass" )
        
    #test update
    createNewFile(add, "add")
    updateFile(add,"update")
    wait()
    
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
    if not os.path.exists(add):
        logToFile( "add from client Fails")
    elif not filecmp.cmp(add,  ClientPath+"/"+"add.txt"):
        logToFile( "add from Server Fails")
    else:
        logToFile( "add from Server Pass" )
        
    #test update
    createNewFile(add, "add")
    updateFile(add,"update")
    wait()
    
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
    
    pool = Pool(processes=3)
    pool.apply_async(Server)
    print "Start server"
    createNewDir(ServerPath)  
    
    time.sleep(30);
    print "Start client"
    createNewDir(ClientPath)  
    pool.apply_async(Client)
    time.sleep(30);
    #pool.apply_async(test)
    
    pool.close()
    
    
    pool.join()
    
    
    
    

    

        
    