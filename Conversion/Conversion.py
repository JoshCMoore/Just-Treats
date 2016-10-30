csvfile = '../treatsout.csv'
jsonfile = csvfile[:csvfile.rfind('.')] + ".json"

infile = open(csvfile, 'r')
outfile = open(jsonfile, 'w+')

outfile.write("eqfeed_callback({\"coordinates\":[\n")


for line in infile:
    coords = line.split(',')
    outfile.write("\t{\"coordinate\":[" + coords[0] + "," +
                  coords[1].rstrip() + "]},\n")

outfile.write("]});")