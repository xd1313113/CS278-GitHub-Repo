cd /vagrant
ant clean
ant init
ant build
ant junit 
ant Server &
sleep 3
ant Client &
