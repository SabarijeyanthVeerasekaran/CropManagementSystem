import json
import cv2
#import boto3
import base64
import numpy as np
def upload(get_file_content):
    decode_content = base64.b64decode(get_file_content)
	# uploading file to S3 bucket
	np_array=np.fromstring(decode_content,np.uint8)
    img_np=cv2.imdecode(np_array,cv2.IMREAD_COLOR)

	#s3_upload = s3.put_object(Bucket="opencvapibucket", Key="file.png", Body=decode_content)
    return (get_file_content)
