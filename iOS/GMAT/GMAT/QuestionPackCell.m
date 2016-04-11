//
//  QuestionPackCell.m
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "QuestionPackCell.h"
#import "Constant.h"

@implementation QuestionPackCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    
    _lblDate.textColor = kAppColor;
    self.backgroundColor = [UIColor clearColor];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];
    
    _separatorView.backgroundColor = [UIColor blackColor];
    _separatorView.alpha = 0.6f;
    
    // Configure the view for the selected state
}

@end
