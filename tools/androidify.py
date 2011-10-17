import os
import glob
import shutil

for file in glob.glob("*/*/*.png"):
	fd = file.split("\\")
	fnfix = "%s_%s_%s" % (fd[-3],fd[-2],fd[-1])
	shutil.copyfile(file, fnfix)
