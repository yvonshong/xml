#!/usr/bin/env python
# encoding: utf-8

"""douban_list_spider.py是一个简单的爬虫，可以根据关键字抓取豆瓣电影、豆瓣读书或者豆瓣音乐的条目信息.
"""


# 把str编码由ascii改为utf8（或gb18030）
import sys
reload(sys)
sys.setdefaultencoding('utf8')

import time
import re
import requests
import string
from bs4 import BeautifulSoup


# ==================== 可配置参数 start ====================

object = 'movie' # 抓取对象
tag_list = ['爱情','科幻','惊悚'] # 感兴趣的任意关键字
page_num = 1 # 每个标签抓取的页数, 必须为正整数


# ==================== 可配置参数 end ====================


file_content = '抓取时间：' + time.asctime() + '\n' # 最终要写到文件里的内容
file_partial_name = '_list.txt'
string_return = '\n\t'


def print_encode(s):
    print s
    # print s.encode("gb18030")

def movie_spider(soup):
    global file_content
#缺少 IMDb     编剧     上映具体时间  语言  片长

    list_soup = soup.findAll(attrs={"class":"pl"})
    for douban_info in list_soup:  #.find_all('span'):
    	#simplestrs=re.sub('|','\n',douban_info)
    	print douban_info
        ######   todo
        





def each_page_spider(douban_tag, page):
    global file_content
    global source_code
    item_num = page * 15 # 每页有15个条目
    url = "http://www.douban.com/tag/%s/%s?start=%d" % (douban_tag, object, item_num)
    item_num += 1
    #############
    # 获取网页内容 
    r = requests.get(url)
    #print r.text
    data = r.text 
    # 利用正则查找所有连接 
    link_list =re.findall(r'<a href="https://movie.douban.com/subject/(.*)/\?from=tag_all" class="title" target="_blank">.*</a>' ,data) 
    #<a href="https://movie.douban.com/subject/19944106/?from=tag"
    for plink in link_list: 
    	eachlink = "http://movie.douban.com/subject/"+plink
    	source_code = requests.get(eachlink)
    	plain_text = source_code.text
    	soup = BeautifulSoup(source_code.text, "lxml")
    	movie_spider(soup)
	  	
    ##############
    




def douban_spider(douban_tag):
    global file_content

    title_divide = '\n' + '--' * 30 + '\n' + '--' * 30 + '\n'
    file_content += title_divide + '\t' * 4 + \
            douban_tag + '：' + title_divide

    for page in range(0, page_num):
        each_page_spider(douban_tag, page)


def do_spider():
    print_encode('准备开始抓取...')
    for douban_tag in tag_list:
        douban_spider(douban_tag)


def do_write():
    """将最终结果写入文件"""
    file_name = object + file_partial_name
    print_encode('正在将抓取信息写入到文件%s中...' % file_name)
    f = open(file_name, 'w')
    f.write(file_content)
    f.close()
    print_encode('抓取完毕，请到文件%s中查看抓取信息...' % file_name)


def main():

    if (type(page_num) != type(1)) or (page_num <= 0):
        print_encode('抓取页数的取值%s无效! 请为page_num设置一个正整数...' % page_num)
        return

    do_spider()
    do_write()

main()

