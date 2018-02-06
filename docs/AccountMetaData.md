
# AccountMetaData

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cosignatories** | [**List&lt;AccountInfo&gt;**](AccountInfo.md) |  |  [optional]
**cosignatoryOf** | [**List&lt;AccountInfo&gt;**](AccountInfo.md) |  |  [optional]
**remoteStatus** | [**RemoteStatusEnum**](#RemoteStatusEnum) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]


<a name="RemoteStatusEnum"></a>
## Enum: RemoteStatusEnum
Name | Value
---- | -----
REMOTE | &quot;REMOTE&quot;
ACTIVATING | &quot;ACTIVATING&quot;
ACTIVE | &quot;ACTIVE&quot;
DEACTIVATING | &quot;DEACTIVATING&quot;
INACTIVE | &quot;INACTIVE&quot;


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
UNKNOWN | &quot;UNKNOWN&quot;
LOCKED | &quot;LOCKED&quot;
UNLOCKED | &quot;UNLOCKED&quot;



