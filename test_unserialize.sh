#!/bin/bash

find serial/ |\
  grep bin |\
  while 
    read FILENAME
  do 
    echo "$(tput setaf 1)$(tput setab 7)>> >> Unserialize ${FILENAME} $(tput sgr 0)"; 
    java DeSerial ${FILENAME}; 
    echo "<<< end "; 
    echo " --- --- ";  
  done

	