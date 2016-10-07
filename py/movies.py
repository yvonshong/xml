#-*-coding:utf-8-*-
import requests
import re

import sys
reload(sys)
sys.setdefaultencoding("utf-8")

import os

class MOVIES(object):
    def __init__(self):
        print u'开始爬取豆瓣电影TOP250...'

    def getsource(self,url):
        html=requests.get(url)
        return html.text

    def getpage(self,url,total_page):
        now_page=int(re.search('start=(\d+)',url,re.S).group(1))
        page_group=[]
        for i in range(now_page,total_page):
            link=re.sub('start=\d+','start=%s' % (i*25),url,re.S)
            page_group.append(link)
        return page_group 
            

    def geteveryclass(self,source):
        everyclass=re.findall('(li id="\d+" test="0" deg="0".*?</li>)',source,re.S)
        return everyclass

    def getinfo(self,eachclass):
        info={}
        info['title']=re.search('<span property="v:itemreviewed">(.*?)</span>',eachclass,re.S).group(1)
        print u'正在爬取:'+info['title']
        info['director']=re.findall('<a.*?rel="v:directedBy">(.*?)</a>',eachclass,re.S)
        stars=re.findall('<a.*?rel="v:starring">(.*?)</a>',eachclass,re.S)
        info['leader_player']=stars[:4]
        info['type']=re.findall('<span property="v:genre">(.*?)</span>',eachclass,re.S)
        temp=re.findall(':</span>(.*?)<br/>',eachclass,re.S)
        release_date=re.search('<span property="v:initialReleaseDate" content="(.*?)">',eachclass,re.S).group(1)
        
        year=release_date[:4]
        #print year
      
        month=release_date[5:7]
        #print month

        day=release_date[8:10]
        #print day

        area=re.search('\((.*?)\)',release_date).group(1)
        
        info['day']=day
        #print info['day']
        info['month']=month
        info['year']=year
        info['area']=area
        info['length']=re.search('<span property="v:runtime".*?>(.*?)</span>',eachclass,re.S).group(1)
        info['score']=re.search('<strong.*?property="v:average">(.*?)</strong>',eachclass,re.S).group(1)
        info['numbers']=re.search('<span property="v:votes">(.*?)</span>',eachclass,re.S).group(1)
        return info

    def saveinfo(self,classinfo):
        f=open('/home/hadoop/movies.xml','a')
        f.write('<?xml version="1.0" encoding="UTF-8"?>\n')
        f.write('<movies>\n')
        for each in classinfo:
            f.write('<movie>\n')
            title='<title>'+each['title']+'</title>\n'
            f.write(title)
            for d in each['director']:
                director='<director>'+d+'</director>\n'
                f.write(director)
            for l in each['leader_player']:
                leader_player='<leader_player>'+l+'</leader_player>\n'
                f.write(leader_player)
            for t in each['type']:
                tye='<type>'+t+'</type>\n'
                f.write(tye)
            f.write('<release_date>\n')
            day='<day>'+each['day']+'</day>\n'
            f.write(day)
            month='<month>'+each['month']+'</month>\n'
            f.write(month)
            year='<year>'+each['year']+'</year>\n'
            f.write(year)
            area='<area>'+each['area']+'</area>\n'
            f.write(area)
            f.write('</release_date>\n')
            length='<length>'+each['length']+'</length>\n'
            f.write(length)
            numbers=each['numbers']
            score=each['score']
            f.write('<score numbers="'+numbers+'">'+score+'</score>\n')
            f.write('</movie>')
        f.write('</movies>\n')
        f.close()

if __name__=='__main__':
    classinfo=[]
    url='https://movie.douban.com/top250?start=0&filter='
    tabu=[u'https://movie.douban.com/subject/1292000/',u'https://movie.douban.com/subject/5912992/',u'https://movie.douban.com/subject/1291583/']
    movies=MOVIES()
    page_group=movies.getpage(url,2)
    movie_group=[]
    for link in page_group:
        page=movies.getsource(link)
        movie=re.findall('<li.*?<div class="item".*?<a href="(.*?)".*?</a>.*?</li>',page,re.S)
        #print movie
        for each in movie:
            movie_group.append(each)
    #print movie_group
    for link in movie_group:
        if link not in tabu:
            page=movies.getsource(link)
            info=movies.getinfo(page)
            classinfo.append(info)
    movies.saveinfo(classinfo)


