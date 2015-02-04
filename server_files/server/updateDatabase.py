import urllib
import re
import MySQLdb
import os
import json
from pymdeco import services
from pyth.plugins.rtf15.reader import Rtf15Reader
from pyth.plugins.plaintext.writer import PlaintextWriter

srv = services.FileMetadataService()

# print "Please enter the database information "

# dhost = raw_input("Host : ")
# duser = raw_input("Username : ")
# dpwd = raw_input("Password : ")
# ddb = raw_input("Database : ")

dhost = "localhost"
duser = "root"
dpwd = ""
ddb = "opensoft"


conn = MySQLdb.connect (host=dhost,user=duser,passwd=dpwd,db=ddb,unix_socket="/opt/lampp/var/mysql/mysql.sock")
x=conn.cursor()
y=conn.cursor()
y.execute("SELECT `id`,`location` , `modified_time` FROM `datainfo`")
data=y.fetchall()

# print data[1]

olist = []
otime = {}
oid = {}
update = {}
update["list"]=[]
update["created"]=0
update["modified"]=0
update["deleted"]=0




for row in data :
    # print str(row[0])+" => " +row[1]
    olist.append(row[1])
    otime[row[1]] = row[2]
    oid[row[1]] = row[0]

# print otime["./data/sample.rtf"]

for dirname, dirnames, filenames in os.walk('./data'):
    # print path to all subdirectories first.
    # for subdirname in dirnames:
    #     print os.path.join(dirname, subdirname)

    # print path to all filenames.
    for filename in filenames:
        # print os.path.join(dirname, filename)
        # print os.path.getsize(os.path.join(dirname, filename))
        loc = os.path.join(dirname, filename)
        fsize = os.path.getsize(loc)
        meta = srv.get_metadata(loc)

        if loc in olist :
            # print meta["file_timestamps"]["modified"]

            if meta["file_timestamps"]["modified"] != otime[loc] :
                y.execute("UPDATE `datainfo` SET `modified_time` = '"+meta["file_timestamps"]["modified"]+"' WHERE `location` = '"+loc.replace("'","\\'")+"'")
                conn.commit()
                modified = {}
                modified['action'] = "modified"
                modified['id'] = oid[loc]
                modified['location'] = loc

                update["list"].append(modified)
                update["modified"]+=1

                print modified
                print "==================================================================="

            olist.remove(loc)

        else :

            # print meta['file_type']
            if filename.split(".~")[0] != "" :
                filename = filename.replace("'","\\'")
                ftype = meta['file_type']
                fext = filename.split('.')[len(filename.split('.'))-1]
                fcat = ''
                ftags = ''
                fmetd = str(meta.to_json(indent=2)).replace("'","\\'")
                floc = os.path.join(dirname, filename)
                modtime = meta["file_timestamps"]["modified"]


                print "Name : " + filename 
                print "Size : " + str(fsize)
                print "Location :" + floc
                print "Extension : "+fext
                print "Type : " + ftype
                print "Modified Time : "+modtime

                if fext == "rtf" :
                	doc = Rtf15Reader.read(open(floc))
                	# print PlaintextWriter.write(doc).getvalue()

                query = "INSERT INTO `datainfo`( `name`, `file type`, `extension`, `size`, `category`, `tags`, `metadata`,`location`,`modified_time`) VALUES ('"+filename+"','"+ftype+"','"+fext+"','"+str(fsize)+"','"+fcat+"','"+ftags+"','"+fmetd+"','"+floc+"','"+modtime+"')"
                
                x.execute(query)
                conn.commit()
                print "================================================================================"

                x.execute("SELECT `id` FROM `datainfo` WHERE `location` = '"+floc+"'")

                xdata = x.fetchall ()

                id = xdata[0][0]

                added = {}
                added['action']="created"
                added['id']=id
                added['location']=loc

                update["list"].append(added)
                update["created"]+=1

# print olist

for floc in olist :
    deleted = {}
    deleted['action']="deleted"
    deleted['id']=oid[floc]
    deleted['location']=floc
    update['list'].append(deleted)
    update["deleted"]+=1

    x.execute("DELETE FROM `datainfo` WHERE `id`='"+str(oid[floc])+"'")
    conn.commit()

    print floc 
    print "===================================================================="

    
# print a
# if "./data/sample.rtf" in a :
#     print "yeah"

update["length"] = len(update["list"])

# print json.dumps(update)

if update["length"] > 0 :
    desc = str(update['created'])+" file(s) created , "+str(update["modified"])+" file(s) modified and "+str(update['deleted'])+" file(s) deleted "

    query = "INSERT INTO `log` (`update`,`desc`) VALUES('"+json.dumps(update).replace("'","\\'")+"','"+desc+"')"
    x.execute(query)
    conn.commit()

    print desc

else :
    print "NO UPDATES"

y.close()

    

