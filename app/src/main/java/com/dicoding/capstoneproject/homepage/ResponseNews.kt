package com.dicoding.capstoneproject.homepage

import com.google.gson.annotations.SerializedName

data class ResponseNews(

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("context")
	val context: Context? = null,

	@field:SerializedName("queries")
	val queries: Queries? = null,

	@field:SerializedName("searchInformation")
	val searchInformation: SearchInformation? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null,

	@field:SerializedName("url")
	val url: Url? = null
)

data class CseThumbnailItem(

	@field:SerializedName("src")
	val src: String? = null,

	@field:SerializedName("width")
	val width: String? = null,

	@field:SerializedName("height")
	val height: String? = null
)

data class ItemsItem(

	@field:SerializedName("snippet")
	val snippet: String? = null,

	@field:SerializedName("htmlFormattedUrl")
	val htmlFormattedUrl: String? = null,

	@field:SerializedName("htmlTitle")
	val htmlTitle: String? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("pagemap")
	val pagemap: Pagemap? = null,

	@field:SerializedName("displayLink")
	val displayLink: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("htmlSnippet")
	val htmlSnippet: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("formattedUrl")
	val formattedUrl: String? = null,

	@field:SerializedName("cacheId")
	val cacheId: String? = null
)

data class ImageobjectItem(

	@field:SerializedName("url")
	val url: String? = null
)

data class SearchInformation(

	@field:SerializedName("searchTime")
	val searchTime: Any? = null,

	@field:SerializedName("totalResults")
	val totalResults: String? = null,

	@field:SerializedName("formattedTotalResults")
	val formattedTotalResults: String? = null,

	@field:SerializedName("formattedSearchTime")
	val formattedSearchTime: String? = null
)

data class Pagemap(

	@field:SerializedName("cse_thumbnail")
	val cseThumbnail: List<CseThumbnailItem?>? = null,

	@field:SerializedName("imageobject")
	val imageobject: List<ImageobjectItem?>? = null,

	@field:SerializedName("metatags")
	val metatags: List<MetatagsItem?>? = null,

	@field:SerializedName("cse_image")
	val cseImage: List<CseImageItem?>? = null,

	@field:SerializedName("webpage")
	val webpage: List<WebpageItem?>? = null
)

data class Queries(

	@field:SerializedName("request")
	val request: List<RequestItem?>? = null,

	@field:SerializedName("nextPage")
	val nextPage: List<NextPageItem?>? = null
)

data class WebpageItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("interactioncount")
	val interactioncount: String? = null,

	@field:SerializedName("datecreated")
	val datecreated: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class RequestItem(

	@field:SerializedName("inputEncoding")
	val inputEncoding: String? = null,

	@field:SerializedName("totalResults")
	val totalResults: String? = null,

	@field:SerializedName("startIndex")
	val startIndex: Int? = null,

	@field:SerializedName("outputEncoding")
	val outputEncoding: String? = null,

	@field:SerializedName("searchTerms")
	val searchTerms: String? = null,

	@field:SerializedName("cx")
	val cx: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("safe")
	val safe: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class NextPageItem(

	@field:SerializedName("inputEncoding")
	val inputEncoding: String? = null,

	@field:SerializedName("totalResults")
	val totalResults: String? = null,

	@field:SerializedName("startIndex")
	val startIndex: Int? = null,

	@field:SerializedName("outputEncoding")
	val outputEncoding: String? = null,

	@field:SerializedName("searchTerms")
	val searchTerms: String? = null,

	@field:SerializedName("cx")
	val cx: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("safe")
	val safe: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class Url(

	@field:SerializedName("template")
	val template: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class MetatagsItem(

	@field:SerializedName("og:image")
	val ogImage: String? = null,

	@field:SerializedName("og:type")
	val ogType: String? = null,

	@field:SerializedName("article:section")
	val articleSection: String? = null,

	@field:SerializedName("og:image:width")
	val ogImageWidth: String? = null,

	@field:SerializedName("og:image:alt")
	val ogImageAlt: String? = null,

	@field:SerializedName("article:published_time")
	val articlePublishedTime: String? = null,

	@field:SerializedName("twitter:card")
	val twitterCard: String? = null,

	@field:SerializedName("twitter:title")
	val twitterTitle: String? = null,

	@field:SerializedName("og:site_name")
	val ogSiteName: String? = null,

	@field:SerializedName("og:title")
	val ogTitle: String? = null,

	@field:SerializedName("og:image:height")
	val ogImageHeight: String? = null,

	@field:SerializedName("og:image:type")
	val ogImageType: String? = null,

	@field:SerializedName("msapplication-tileimage")
	val msapplicationTileimage: String? = null,

	@field:SerializedName("og:description")
	val ogDescription: String? = null,

	@field:SerializedName("og:image:secure_url")
	val ogImageSecureUrl: String? = null,

	@field:SerializedName("twitter:image")
	val twitterImage: String? = null,

	@field:SerializedName("article:tag")
	val articleTag: String? = null,

	@field:SerializedName("viewport")
	val viewport: String? = null,

	@field:SerializedName("twitter:description")
	val twitterDescription: String? = null,

	@field:SerializedName("og:locale")
	val ogLocale: String? = null,

	@field:SerializedName("og:url")
	val ogUrl: String? = null,

	@field:SerializedName("dtk:contenttype")
	val dtkContenttype: String? = null,

	@field:SerializedName("dtk:custom-pagetype")
	val dtkCustomPagetype: String? = null,

	@field:SerializedName("dtk:publishdate")
	val dtkPublishdate: String? = null,

	@field:SerializedName("dtk:kanalid")
	val dtkKanalid: String? = null,

	@field:SerializedName("createdate")
	val createdate: String? = null,

	@field:SerializedName("dtk:publishdateunix")
	val dtkPublishdateunix: String? = null,

	@field:SerializedName("twitter:creator")
	val twitterCreator: String? = null,

	@field:SerializedName("dtk:acctype")
	val dtkAcctype: String? = null,

	@field:SerializedName("dtk:keywords")
	val dtkKeywords: String? = null,

	@field:SerializedName("article:publisher")
	val articlePublisher: String? = null,

	@field:SerializedName("dtk:articledewasa")
	val dtkArticledewasa: String? = null,

	@field:SerializedName("originaltitle")
	val originaltitle: String? = null,

	@field:SerializedName("dtk:articletype")
	val dtkArticletype: String? = null,

	@field:SerializedName("tag")
	val tag: String? = null,

	@field:SerializedName("kanalid")
	val kanalid: String? = null,

	@field:SerializedName("twitter:site:id")
	val twitterSiteId: String? = null,

	@field:SerializedName("dtk:subacctype")
	val dtkSubacctype: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("dtk:author")
	val dtkAuthor: String? = null,

	@field:SerializedName("fb:pages")
	val fbPages: String? = null,

	@field:SerializedName("article:author")
	val articleAuthor: String? = null,

	@field:SerializedName("twitter:image:src")
	val twitterImageSrc: String? = null,

	@field:SerializedName("dtk:custom-title")
	val dtkCustomTitle: String? = null,

	@field:SerializedName("google-play-app")
	val googlePlayApp: String? = null,

	@field:SerializedName("dtk:namakanal")
	val dtkNamakanal: String? = null,

	@field:SerializedName("idfokus")
	val idfokus: String? = null,

	@field:SerializedName("dtk:platform")
	val dtkPlatform: String? = null,

	@field:SerializedName("dtk:thumbnailurl")
	val dtkThumbnailurl: String? = null,

	@field:SerializedName("articleid")
	val articleid: String? = null,

	@field:SerializedName("dtk:videopresent")
	val dtkVideopresent: String? = null,

	@field:SerializedName("platform")
	val platform: String? = null,

	@field:SerializedName("twitter:site")
	val twitterSite: String? = null,

	@field:SerializedName("dtk:articlehoax")
	val dtkArticlehoax: String? = null,

	@field:SerializedName("publishdate")
	val publishdate: String? = null,

	@field:SerializedName("articletype")
	val articletype: String? = null,

	@field:SerializedName("videopresent")
	val videopresent: String? = null,

	@field:SerializedName("googlebot-news")
	val googlebotNews: String? = null,

	@field:SerializedName("dtk:createddateunix")
	val dtkCreateddateunix: String? = null,

	@field:SerializedName("contenttype")
	val contenttype: String? = null,

	@field:SerializedName("fb:app_id")
	val fbAppId: String? = null,

	@field:SerializedName("dtk:articleid")
	val dtkArticleid: String? = null,

	@field:SerializedName("dtk:custom-type")
	val dtkCustomType: String? = null,

	@field:SerializedName("apple-mobile-web-app-capable")
	val appleMobileWebAppCapable: String? = null,

	@field:SerializedName("dtk:createddate")
	val dtkCreateddate: String? = null,

	@field:SerializedName("owner")
	val owner: String? = null,

	@field:SerializedName("coverage")
	val coverage: String? = null,

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("subject")
	val subject: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("webcrawlers")
	val webcrawlers: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("designer")
	val designer: String? = null,

	@field:SerializedName("classification")
	val classification: String? = null,

	@field:SerializedName("distribution")
	val distribution: String? = null,

	@field:SerializedName("spiders")
	val spiders: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("revised")
	val revised: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("reply-to")
	val replyTo: String? = null,

	@field:SerializedName("identifier-url")
	val identifierUrl: String? = null,

	@field:SerializedName("handheldfriendly")
	val handheldfriendly: String? = null,

	@field:SerializedName("facebook-domain-verification")
	val facebookDomainVerification: String? = null,

	@field:SerializedName("mobileoptimized")
	val mobileoptimized: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class CseImageItem(

	@field:SerializedName("src")
	val src: String? = null
)

data class Context(

	@field:SerializedName("title")
	val title: String? = null
)
