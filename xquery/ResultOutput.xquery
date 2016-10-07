xquery version "1.0";
<html>
	<head>
		<title>排序结果</title>
	</head>
<body>
	<h1>1.按上映时间排序(仅显示电影名、导演和上映时间)：</h1>
	<table boder="3">
		<tr>
			<th>电影名</th>
			<th>导演</th>
			<th>上映时间</th>
		</tr>
	{	
		for $movie in doc("movie.xml")/movies/movie
		let $movie_name:=$movie/movie_name,$director:=$movie/director,$release_time:=$movie/release_time
		order by $release_time 
		return 
		<tr>
			<td>{data($movie_name)}</td>
			<td>{data($director )}</td>
			<td>{data($release_time)}</td>
		</tr>
	}
	</table>
	<h2>2.选择时长在130以下，评分在8.0以上的电影（仅显示电影名，时长，评分）</h2>
	<table boder="3">
		<tr>
			<th>电影名</th>
			<th>时长</th>
			<th>评分</th>
		</tr>
	{	
		for $movie in doc("movie.xml")/movies/movie
		let $movie_name:=$movie/movie_name,$length:=$movie/length,$score:=$movie/score
		where $score > 8.0 and $length < 140
		order by $score descending
		return 
		<tr>
			<td>{data($movie_name)}</td>
			<td>{data($length  )}</td>
			<td>{data($score)}</td>
		</tr>
	}
	
	</table>
	
	<h3>3.选择 詹姆斯_卡梅隆 指导的电影中，评分最高的三部电影：</h3>
	<table boder ="3">
		<tr>电影名</tr>
		<tr>评分</tr>
		<tr>上映时间</tr>
	{
		let $movielist:=(
			            for $movie in doc("movie.xml")/movies/movie
			            let $movie_name:=$movie/movie_name
			            let $score:=$movie/score
			            let $release_time:=$movie/release_time
			            let $director:=$movie/director
			            where $director = "詹姆斯_卡梅隆"
			            order by $score descending
			            return 
								<movie>
								{$movie/@*}
								{$movie/*}
								</movie>
						)
		return 
			for $resultlist at $scoreindex in $movielist 
			let $name:=$resultlist/movie_name
			let $score:=$resultlist/score
			let $release_time:=$resultlist/release_time
		return 
			if ($scoreindex <=3 ) then 
								<tr>
								<td>{data($name)}</td>
								<td>{data($score)}</td>
								<td>{data($release_time)}</td>
								</tr>
			else
			''
	}
	
	</table>
	
	<h4>4.以语种为跟元素重新整理成新的文档（今显示语种以及电影名称）：</h4>
							<language>
						    {
								for $lang in distinct-values(
								doc("movie.xml")/movies/movie/language/lang)
								return
								<lang name="{$lang}">
								{
								for $director in distinct-values(doc("movie.xml")/movies/movie/director)
								let $movies := doc("movie.xml")/movies/movie
								where
								some $movie in $movies
								satisfies ($movie/director = $director and $movie/language/lang =
								$lang)
								return
								<director name="{$director}">
								{
								for $aMovie in doc("movie.xml")/movies/movie
								let $aMovieDirector := $aMovie/director
								where $aMovieDirector = $director
								return
									<movie>
									{$aMovie/@*}
									{$aMovie/*}
									</movie>
								}
								</director>
								}
								</lang>
								}
							</language>
	
</body>
</html>
