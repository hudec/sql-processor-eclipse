# !/bin/bash

PluginsDir=plugins
FeaturesDir=features
PluginIDSeparator=_
RemovingScriptFileName=remove-old-eclipse-plugins.txt
rm -rf $RemovingScriptFileName

#for dir in $PluginsDir $FeaturesDir
for dir in $PluginsDir  # $FeaturesDir: most file names in features dir contains more than 1 _ character
do
    echo "Processing [$dir] directory..."
    # split PluginID from filename
    # (not reliable, but general working. (ex: will get one junit PluginID because there're move than 1 _ characters in file name))
    file_list=$(ls $dir);
    echo "$file_list" | cut -f1 -d $PluginIDSeparator         > $dir-all.txt
    echo "$file_list" | cut -f1 -d $PluginIDSeparator  | uniq > $dir-uniq.txt

    # get the PluginList which VERY POSSIBLY has old versions
    diff_result=$(diff -U 0 $dir-uniq.txt $dir-all.txt)
    plugins_which_has_old_versions=$(echo "$diff_result" | grep -e "^+[^+]" | cut -f 2 -d +)

    #
    for p in $(echo "$plugins_which_has_old_versions")
    do
        echo "$p"
        i=0
        for f in $(ls -d -t $dir/$p$PluginIDSeparator*) # use 'ls' command, can sort result by file time, but can not handle file name contains special characters (white space) when using wildcard
        #for f in $(find $dir -name "$p$PluginIDSeparator*")    # use 'find' command
        do
            if [ -d $f ]
            then
                # should use rm -rf
                echo -n "[D]"
            else
                echo -n "   "
            fi
            echo -n "$f"

            ((i++))
            if [ $i -eq 1 ]
            then
                echo ""
                continue    # first file, the newest version
            fi
            echo "    [old]"
            echo "rm -rf $f" >> $RemovingScriptFileName
        done

        echo
    done
done

