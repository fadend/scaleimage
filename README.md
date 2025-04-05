# Scale images

This is a basic utility to scale images in place.

## Usage

Requires [Maven](https://maven.apache.org/).

### Download and build

```
git clone https://github.com/fadend/scaleimage
cd scaleimage
mvn package
```

### Run

```
java -jar target/scaleimage-*-jar-with-dependencies.jar \
  --max-width=1000 \
  --max-height=1000 \
  $HOME/Downloads/yampah/*.jpg
```
