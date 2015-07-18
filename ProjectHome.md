Generates map tiles for Google Maps. This is a java version of the excellent work done by the maptiler folks.

The computation of projections is a translation of their python code to java

# Usage #
This was developed to generate tiles from 4+ million points representing elevations obtained from depth soundings. The resulting tiles were hosted by at http://dsm2bathymetry.appspot.com and the code for using those tiles and uploading and displaying a map rendering them is hosted [here](http://code.google.com/p/dsm2-grid-map)

One of the features added was the ability to work with thousands of images by maintaining an image cache on the disk and being able to work with rendering millions of points on them. This should easily scale to as large a heat map generation as is necessary.