# Log Mapping Reverse
Reverse Log Mapping Processor output mapping.txt to readable log format

## Usage
```shell script
  java -jar log-mapping-reverse-1.0.0.jar \
      --input-log <path_to_log_file> \
      --output-log <path_to_processed_log_file> \
      --mapping-file <path_to_mapping_txt>
```
```shell script
  adb logcat | java -jar log-mapping-reverse-1.0.0.jar \
      --mapping-file <path_to_mapping_txt>
```

## Compiling
To compile Log Mapping Processor, you need a Java Development Kit 1.8 (JDK8)
```shell script
git clone https://github.com/eritpchy/log-mapping-reverse
cd log-mapping-reverse
./gradlew jar
```

## References
- [Log Mapping Processor](https://github.com/eritpchy/log-mapping-processor)
- [Log Mapping Processor Android Gradle plugin](https://github.com/eritpchy/log-mapping-processor-android-gradle-plugin)
- [JavaParser](https://github.com/javaparser/javaparser)