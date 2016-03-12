//
//  Question.h
//  GMAT
//
//  Created by Trung Đức on 3/12/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Question : NSObject

@property(nonatomic,strong) NSString *id;
@property(nonatomic,strong) NSString *answerA;
@property(nonatomic,strong) NSString *answerB;
@property(nonatomic,strong) NSString *answerC;
@property(nonatomic,strong) NSString *answerD;
@property(nonatomic,strong) NSString *answerE;
@property(nonatomic,strong) NSString *explanation;
@property(nonatomic,strong) NSNumber *right_answer;
@property(nonatomic,strong) NSString *stem;
@property(nonatomic,strong) NSString *stimulus;
@property(nonatomic,strong) NSString *subType;
@property(nonatomic,strong) NSString *type;


- (instancetype)initWithJson:(NSDictionary *)json;

@end
