## Requirement

## API Reference

#### Get all data

```http
  GET /api/v1/getAll
```

#### Create new data

```http
  POST /api/v1/insert
```

| Parameter | Type | Description | constrain |
| :-------- | :--- | :---------- | :---------|
| `name`      | `String` | `name of customer ` |`NotNull`  |
| `city` | `String` | `city name` |`NotNull`  |
| `country`|`String` |`country name ` |`NotNull`  |
| `pincode`|`String` |`area pincode ` |`NotNull`  |
| `satScore`|`double` |`obtained SAT score ` |`0-1600`,`NotNull`  |

#### Get rank by name

```http
  GET /api/v1/getRank/{name}
```


#### Update data by name 

```http
  PUT /api/v1/updateScore
```
| Parameter | Type | Description | constrain |
| :-------- | :--- | :---------- | :---------|
| `name`      | `String` | `name of customer ` |`NotNull`  |
| `satScore`|`double` |`obtained SAT score ` |`0-1600`,`NotNull`  |

#### delete by name

```http
  DELETE /api/v1/delete/{name}
```
