# Ip2region

标准化的数据格式

每条ip数据段都固定了格式：

_城市Id|国家|区域|省份|城市|ISP_

## db maker java implementation
### How to make ?
```
java -jar dbMaker-{version}.jar -src path-of-ip.merge.txt -region path-of-global_region.csv -dst path-of-ip2region.db
```

### For example
Run the command in the current directory
```
java -jar dbMaker-1.2.2.jar -src data/ip.merge.txt -region data/global_region.csv -dst data/ip2region.db
```