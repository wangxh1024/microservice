FROM python:3.7.1

MAINTAINER wangxh m13656657690@163.com

RUN pip install thrift

ENV PYTHONPATH /

COPY ./message /message

ENTRYPOINT ["python", "/message/MessageService.py"]