# Release instructions

Update license headers in all files

```
mvn license:format
```

Create tag, prepare version

```
mvn release:clean release:prepare
```

Stage the release

```
mvn release:perform
```

Promote release in [central](https://oss.sonatype.org/)

```
Staging Repositories > Choose the release > Close > Release
```